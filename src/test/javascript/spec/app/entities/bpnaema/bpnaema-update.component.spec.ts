import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpnaemaUpdateComponent } from 'app/entities/bpnaema/bpnaema-update.component';
import { BpnaemaService } from 'app/entities/bpnaema/bpnaema.service';
import { Bpnaema } from 'app/shared/model/bpnaema.model';

describe('Component Tests', () => {
  describe('Bpnaema Management Update Component', () => {
    let comp: BpnaemaUpdateComponent;
    let fixture: ComponentFixture<BpnaemaUpdateComponent>;
    let service: BpnaemaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpnaemaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BpnaemaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BpnaemaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BpnaemaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bpnaema(123);
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
        const entity = new Bpnaema();
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
