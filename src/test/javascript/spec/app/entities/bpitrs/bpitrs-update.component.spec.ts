import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpitrsUpdateComponent } from 'app/entities/bpitrs/bpitrs-update.component';
import { BpitrsService } from 'app/entities/bpitrs/bpitrs.service';
import { Bpitrs } from 'app/shared/model/bpitrs.model';

describe('Component Tests', () => {
  describe('Bpitrs Management Update Component', () => {
    let comp: BpitrsUpdateComponent;
    let fixture: ComponentFixture<BpitrsUpdateComponent>;
    let service: BpitrsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpitrsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BpitrsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BpitrsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BpitrsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bpitrs(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bpitrs();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
