import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BpbqeUpdateComponent } from 'app/entities/bpbqe/bpbqe-update.component';
import { BpbqeService } from 'app/entities/bpbqe/bpbqe.service';
import { Bpbqe } from 'app/shared/model/bpbqe.model';

describe('Component Tests', () => {
  describe('Bpbqe Management Update Component', () => {
    let comp: BpbqeUpdateComponent;
    let fixture: ComponentFixture<BpbqeUpdateComponent>;
    let service: BpbqeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BpbqeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BpbqeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BpbqeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BpbqeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bpbqe(123);
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
        const entity = new Bpbqe();
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
