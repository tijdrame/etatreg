import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpdeviseUpdateComponent } from 'app/entities/bpdevise/bpdevise-update.component';
import { BpdeviseService } from 'app/entities/bpdevise/bpdevise.service';
import { Bpdevise } from 'app/shared/model/bpdevise.model';

describe('Component Tests', () => {
  describe('Bpdevise Management Update Component', () => {
    let comp: BpdeviseUpdateComponent;
    let fixture: ComponentFixture<BpdeviseUpdateComponent>;
    let service: BpdeviseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpdeviseUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BpdeviseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BpdeviseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BpdeviseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bpdevise(123);
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
        const entity = new Bpdevise();
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
